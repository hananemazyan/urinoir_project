package com.example.urinoirapp.Controller;


import com.example.urinoirapp.Model.Device;
import com.example.urinoirapp.Model.Patient;
import com.example.urinoirapp.Model.TestData;
import com.example.urinoirapp.Service.DeviceService;
import com.example.urinoirapp.Service.PatientService;
import com.example.urinoirapp.Service.ReferenceDataService;
import com.example.urinoirapp.Service.TestDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Controller
public class TestDataController {

    @Autowired
    private TestDataService testDataService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private PatientService patientService;
    private ReferenceDataService referenceDataService;
    @Autowired
    private WebSocketController webSocketController;

    private List<TestData> temporaryTestDataList = new ArrayList<>();
    private AtomicLong testIdGenerator = new AtomicLong(1);

    /*  @GetMapping("/addTestData")
      public String addTestData(
              @RequestParam("second") int second,
              @RequestParam("volume") double volume,
              @RequestParam("patientId") Long patientId,
              @RequestParam("deviceId") Long deviceId,
              Model model
      ) {
          System.out.println("Second value received: " + second);
          Patient patient = patientService.getPatientById(patientId);
          Device device = deviceService.retrieveDeviceById(deviceId);

          if (patient == null || device == null) {
              model.addAttribute("message", "Patient or device not found.");
              return "error";
          }

          TestData testData = new TestData();
          testData.setTestDate(LocalDate.now());
          testData.setRecordedAt(LocalDateTime.now());
          testData.setPatient(patient);
          testData.setDevice(device);
          testData.setSecond(second);
          testData.setVolume(volume);

          temporaryTestDataList.add(testData);

          if (temporaryTestDataList.size() == 6) {
              Long testId = testIdGenerator.getAndIncrement();
              for (TestData td : temporaryTestDataList) {
                  td.setTestId(testId);
                  testDataService.saveTestData(td);
              }
              temporaryTestDataList.clear();
              model.addAttribute("message", "Test data saved successfully.");
              return "Dashboard";
          } else {
              model.addAttribute("message", "Waiting for more data. Current count: " + temporaryTestDataList.size());
              return "Dashboard";
          }
      }
  */
    @GetMapping("/addTestData")
    public String addTestData(
            @RequestParam("second") int second,
            @RequestParam("volume") double volume,
            @RequestParam("patientId") Long patientId,
            @RequestParam("deviceId") Long deviceId,
            Model model
    ) {
        System.out.println("Second value received: " + second);
        Patient patient = patientService.getPatientById(patientId);
        Device device = deviceService.retrieveDeviceById(deviceId);

        if (patient == null || device == null) {
            model.addAttribute("message", "Patient or device not found.");
            return "error";
        }

        TestData testData = new TestData();
        testData.setTestDate(LocalDate.now());
        testData.setRecordedAt(LocalDateTime.now());
        testData.setPatient(patient);
        testData.setDevice(device);
        testData.setSecond(second);
        testData.setVolume(volume);

        temporaryTestDataList.add(testData);

        // Send the data through WebSocket
        webSocketController.sendTestDataUpdate(testData);

        if (temporaryTestDataList.size() == 6) {
            Long testId = testIdGenerator.getAndIncrement();
            for (TestData td : temporaryTestDataList) {
                td.setTestId(testId);
                testDataService.saveTestData(td);
            }
            temporaryTestDataList.clear();
            model.addAttribute("message", "Test data saved successfully.");
            return "dashboard_patient";
        } else {
            model.addAttribute("message", "Waiting for more data. Current count: " + temporaryTestDataList.size());
            return "dashboard_patient";
        }
    }

    @GetMapping("/dashboard_patient/{patientId}")
    public String visualizeTestData(Model model) {
        List<TestData> allTestData = testDataService.getAllTestData();
        List<Double> volumeList = allTestData.stream()
                .map(TestData::getVolume)
                .collect(Collectors.toList());

        List<Integer> timeList = allTestData.stream()
                .map(TestData::getSecond)
                .collect(Collectors.toList());

        model.addAttribute("volume", volumeList);
        model.addAttribute("time", timeList);

        CacheControl cacheControl = CacheControl.noCache().mustRevalidate();
        return "dashboard_patient";
    }

    @GetMapping("/visualizeTestDevice/{deviceId}")
    public String visualizeTestDataForDevice(@PathVariable Long deviceId, Model model) {
        List<TestData> testDataForDevice = testDataService.getAllTestData().stream()
                .filter(testData -> testData.getDevice() != null && testData.getDevice().getId().equals(deviceId))
                .collect(Collectors.toList());

        List<Double> volumeList = testDataForDevice.stream()
                .map(TestData::getVolume)
                .collect(Collectors.toList());

        List<Integer> timeList = testDataForDevice.stream()
                .map(TestData::getSecond)
                .collect(Collectors.toList());

        model.addAttribute("volume", volumeList);
        model.addAttribute("time", timeList);

        return "dashboard_patient";
    }

    @GetMapping("/dashboard_patient")
    public String visualizeTestDataForPatient(@RequestParam(value = "patientId", required = false) Long patientId, Model model) {
        if (patientId == null) {
            // Handle case when patientId is not provided
            model.addAttribute("message", "Patient ID is required.");
            return "error"; // Or any other appropriate action
        }

        List<TestData> testDataForPatient = testDataService.getAllTestData().stream()
                .filter(testData -> testData.getPatient() != null && testData.getPatient().getId().equals(patientId))
                .collect(Collectors.toList());

        List<Double> volumeList = testDataForPatient.stream()
                .map(TestData::getVolume)
                .collect(Collectors.toList());

        List<Integer> timeList = testDataForPatient.stream()
                .map(TestData::getSecond)
                .collect(Collectors.toList());

        model.addAttribute("volume", volumeList);
        model.addAttribute("time", timeList);
        model.addAttribute("patientId", patientId);  // Add patientId to the model

        return "dashboard_patient";
    }

    @GetMapping("/fetchDashboardData")
    @ResponseBody
    public List<List<Object>> fetchDashboardData(@RequestParam(value = "patientId") Long patientId) {
        List<TestData> patientTestData = testDataService.getAllTestDataForPatient(patientId);
        List<List<Object>> DashboardData = new ArrayList<>();

        for (TestData testData : patientTestData) {
            List<Object> dataPoint = new ArrayList<>();
            dataPoint.add(testData.getSecond());
            dataPoint.add(testData.getVolume());
            DashboardData.add(dataPoint);
        }

        DashboardData.sort(Comparator.comparingInt(o -> (int) o.get(0)));

        return DashboardData;
    }

    @GetMapping("/history/{patientId}")
    public String getAllTestsForPatient(@PathVariable Long patientId, Model model) {
        List<TestData> allTests = testDataService.getAllTestDataForPatient(patientId);
        List<List<TestData>> groupedTests = new ArrayList<>();
        List<TestData> currentTestGroup = new ArrayList<>();

        for (TestData testData : allTests) {
            currentTestGroup.add(testData);
            if (currentTestGroup.size() == 6) {
                groupedTests.add(new ArrayList<>(currentTestGroup));
                currentTestGroup.clear();
            }
        }

        if (!currentTestGroup.isEmpty()) {
            groupedTests.add(new ArrayList<>(currentTestGroup));
        }

        model.addAttribute("groupedTests", groupedTests);
        model.addAttribute("patientId", patientId);

        return "History";
    }

    @GetMapping("/viewGraph")
    public String viewGraph(
            @RequestParam("patientId") Long patientId,
            @RequestParam("testId") Long testId,
            Model model) {
        List<List<Object>> graphData = fetchGraphDataById(testId);
        model.addAttribute("graphData", graphData);
        model.addAttribute("patientId", patientId);
        model.addAttribute("testId", testId);

        return "viewGraph";
    }


    @GetMapping("/fetchGraphDataById")
    @ResponseBody
    public List<List<Object>> fetchGraphDataById(@RequestParam("testId") Long testId) {
        List<TestData> patientTestData = testDataService.getAllTestData().stream()
                .filter(testData -> {
                    Long testIdValue = testData.getTestId();
                    return testIdValue != null && testIdValue.equals(testId);
                })
                .collect(Collectors.toList());

        List<List<Object>> graphData = new ArrayList<>();

        for (TestData testData : patientTestData) {
            List<Object> dataPoint = new ArrayList<>();
            dataPoint.add(testData.getSecond());
            dataPoint.add(testData.getVolume());
            graphData.add(dataPoint);
        }

        graphData.sort(Comparator.comparingInt(o -> (int) o.get(0)));

        System.out.println("Graph data response: " + graphData);

        return graphData;
    }


    @GetMapping("/fetchGraphData")
    @ResponseBody
    public List<List<Object>> fetchGraphData(
            @RequestParam(value = "patientId") Long patientId,
            @RequestParam(value = "testId", required = false) Long testId) {
        List<TestData> patientTestData;

        if (testId != null) {
            // If test ID is provided, filter by both patient ID and test ID
            patientTestData = testDataService.getAllTestDataForPatientAndTest(patientId, testId);
        } else {
            // If test ID is not provided, fetch all data for the patient
            patientTestData = testDataService.getAllTestDataForPatient(patientId);
        }

        List<List<Object>> graphData = new ArrayList<>();

        for (TestData testData : patientTestData) {
            List<Object> dataPoint = new ArrayList<>();
            dataPoint.add(testData.getSecond());
            dataPoint.add(testData.getVolume());
            graphData.add(dataPoint);
        }

        graphData.sort(Comparator.comparingInt(o -> (int) o.get(0)));

        return graphData;
    }

    @GetMapping("/account/{patientId}")
    public String getPatientAccount(@PathVariable Long patientId, Model model) {
        Patient patient = patientService.getPatientById(patientId);
        if (patient == null) {
            model.addAttribute("message", "Patient not found.");
            return "error";
        }
        model.addAttribute("patient", patient);
        model.addAttribute("patientId", patientId);  // Add patientId to the model
        return "account";
    }


    @GetMapping("/fetchTestData/{patientId}")
    @ResponseBody
    public Map<String, Object> fetchTestData(@PathVariable Long patientId) {
        Map<String, Object> testDataInfo = new HashMap<>();

        // Fetch patient information from the database based on the provided patient ID
        Patient patient = patientService.getPatientById(patientId);

        if (patient == null) {
            testDataInfo.put("error", "Patient not found");
            return testDataInfo;
        }

        // Fetch test data associated with the patient
        List<TestData> patientTestData = testDataService.getAllTestDataForPatient(patientId);

        // Populate the response map with patient name and test data
        testDataInfo.put("patientName", patient.getFirstname()); // Assuming "nom" is the attribute for patient name
        testDataInfo.put("testData", patientTestData);

        return testDataInfo;
    }
    @GetMapping("/settings")
    public String settingsPage(@RequestParam("patientId") Long patientId, Model model) {
        // You can add logic here to retrieve patient-specific settings data based on the patientId
        model.addAttribute("patientId", patientId);
        return "settings"; // Assuming "settings" is the name of your settings page HTML file
    }
    // New method to handle live data updates
    @GetMapping("/liveUpdate")
    @ResponseBody
    public Map<String, Object> liveUpdate(@RequestParam("patientId") Long patientId) {
        Map<String, Object> liveData = new HashMap<>();

        List<TestData> patientTestData = testDataService.getAllTestDataForPatient(patientId);
        liveData.put("testData", patientTestData);

        return liveData;
    }

    // New method to handle redirection to Live.html
    @GetMapping("/redirectLive")
    public String redirectLive(@RequestParam("patientId") Long patientId, Model model) {
        model.addAttribute("patientId", patientId);
        return "Live";
    }



}
