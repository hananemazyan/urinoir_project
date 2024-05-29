package com.example.urinoirapp.Controller;


import com.example.urinoirapp.Model.Device;
import com.example.urinoirapp.Model.Patient;
import com.example.urinoirapp.Model.Secretaire;
import com.example.urinoirapp.Model.TestData;
import com.example.urinoirapp.Repository.PatientRepository;
import com.example.urinoirapp.Service.DeviceService;
import com.example.urinoirapp.Service.PatientService;
import com.example.urinoirapp.Service.QRCodeGenerator;
import com.example.urinoirapp.Service.TestDataService;
import com.example.urinoirapp.Service.impl.PatientServiceImpl;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Controller
public class PatientController {

    @Autowired
    private TestDataService testDataService;
@Autowired
    private PatientRepository patientRepository;
@Autowired
    private PatientService patientService;

    @Autowired
    private QRCodeGenerator qrCodeGenerator;
    @Autowired
    private PatientServiceImpl patientServiceImpl;

    private List<TestData> temporaryTestDataDataList = new ArrayList<> ();
    private AtomicLong testIdGenerator = new AtomicLong(1);

    @Autowired
    private DeviceService deviceService;


    @Autowired
    public PatientController(PatientRepository patientRepository, PatientService patientService) {
        this.patientRepository = patientRepository;
        this.patientService = patientService;
    }

    // handler method to handle list medecins and return mode and view
    @GetMapping("/patients")
    public String listPatient(Model model) {
        model.addAttribute("patients", patientService.getAllPatient ());
        return "patients";
    }

//    @GetMapping("/index")
//    public String home() {
//        return "index";
//    }

    @GetMapping("/index")
    public String getDashboard(Model model) {
        long countPatients = patientService.countPatients();
        model.addAttribute("countPatients", countPatients);
        return "index"; // Le nom de votre template Thymeleaf
    }



    @GetMapping("/patients/new")
    public String createPatientForm(Model model) {
        // create object patient to hold patient form data
       Patient patient = new Patient();
        model.addAttribute("patient", patient);
        return "create_patient";

    }

    @PostMapping("/patients")
    public String savePatients(@ModelAttribute("patient") Patient patient, Model model) {
        // Ensure serial code is generated
        // Save the patient to the database
        Patient savedPatient = patientService.savePatient(patient);

        // Generate QR code for the patient
        String qrCodePath = "C:\\Users\\D\\Desktop\\qrCode\\qrCode\\src\\main\\resources\\static\\images\\" + savedPatient.getId() + "_QRCode.png";
        try {
//            qrCodeGenerator.generateQRCode(savedPatient.getId(), savedPatient.getSerialCode(), qrCodePath, 200, 200);
            qrCodeGenerator.generateQRCode(savedPatient.getId(), savedPatient.getSerialCode(), qrCodePath, 200, 200);

            savedPatient.setQrCodePath("/images/" + savedPatient.getId() + "_QRCode.png");
            patientService.updatePatient(savedPatient);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }

        // Set the patient and QR code URL in the model to display them immediately
        model.addAttribute("patient", savedPatient);
        String qrCodeUrl = "/images/" + savedPatient.getId() + "_QRCode.png";
        model.addAttribute("qrCodeUrl", qrCodeUrl);

        // Redirect to the patient details page
        return "patient-qr";
    }



    @GetMapping("/patients/{id}/qr-code-url")
    @ResponseBody
    public String getQrCodeUrl(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        return "/images/" + patient.getId() + "_QRCode.png";
    }


//    @GetMapping("/patients/edit/{id}")
//        public String editPatientForm(@PathVariable Long id, Model model) {
//        Patient patient = patientService.getPatientById(id);
//        model.addAttribute("patient", patient);
//        return "edit_patient";
//        }

        @PostMapping("/patients/{id}")
        public String updatePatient(@PathVariable Long id,
                                    @ModelAttribute("patient") Patient patient,
                                    Model model) {

            // Get patient from database by id
            Patient existingPatient = patientService.getPatientById(id);
            existingPatient.setId(id);
            existingPatient.setFirstname (patient.getFirstname());
            existingPatient.setLastname (patient.getLastname());
            existingPatient.setCIN (patient.getCIN ());
            existingPatient.setAdresse (patient.getAdresse ());
            existingPatient.setDateOfBirth (patient.getDateOfBirth ());
            existingPatient.setTelephone (patient.getTelephone ());
            existingPatient.setEmail(patient.getEmail());
            existingPatient.setHealthProblem (patient.getHealthProblem ());
            existingPatient.setAllergy (patient.getAllergy ());
            existingPatient.setSerialCode ( patient.getSerialCode () );
            // Save updated patient object
            patientService.updatePatient(existingPatient);
            return "redirect:/patients";
        }



    @GetMapping("/patients/edit/{id}")
    public String editPatientForm(@PathVariable Long id, Model model) {
        Patient patient = patientService.getPatientById(id);
        if (patient != null) {
            model.addAttribute("patient", patient);
            return "edit_patient";
        }
        return "redirect:/patients"; // Gérer le cas où le patient n'existe pas
    }


    // Handler method to handle delete patient request
        @GetMapping("/patients/{id}")
        public String deletePatients(@PathVariable Long id) {
            patientService.deletePatientById(id);
            return "redirect:/patients";
        }




    // Nouvelle méthode pour afficher les informations du patient et le QR code
    @GetMapping("/patients/{id}/qr")
    public String getPatientQrPage(@PathVariable Long id, Model model) {
        Patient patient = patientService.getPatientById(id);
        if (patient != null) {
            model.addAttribute("patient", patient);
            String qrCodeUrl = generateQrCodeUrl(patient);
            model.addAttribute("qrCodeUrl", qrCodeUrl); // Ajouter l'URL du code QR au modèle
            return "patient-qr";
        }
        return "redirect:/patient-qr"; // Gérer le cas où le patient n'existe pas
    }


    private String generateQrCodeUrl(Patient patient) {
        return "/images/" + patient.getId() + "_QRCode.png";
    }



    @GetMapping("/loginusers")
    public String showLoginForm(Model model) {
        model.addAttribute("Secretaire", new Secretaire ());
        return "loginusers";
    }

    @GetMapping("/patient/{id}")
    public String getPatientHistory(@PathVariable Long id, Model model) {
        Patient patient = patientService.getPatientById(id);
        model.addAttribute("patient", patient);
        // Ajoutez ici le code pour récupérer l'historique du patient
        return "Historique";
    }
    @GetMapping("/Historique")
    public String listPatientHistory(Model model) {
        model.addAttribute("patients", patientService.getAllPatient ());
        return "Historique";
    }


    @GetMapping("/patient/{patientId}/testHistory")
    public String getAllTestsForPatient(@PathVariable Long patientId, Model model) {
        List<TestData> allTestData = testDataService.getAllTestDataForPatient(patientId);
        List<List<TestData>> groupedTests = new ArrayList<>();
        List<TestData> currentTestDataGroup = new ArrayList<>();

        for (TestData testData : allTestData) {
            currentTestDataGroup.add(testData);
            if (currentTestDataGroup.size() == 6) {
                groupedTests.add(new ArrayList<>(currentTestDataGroup));
                currentTestDataGroup.clear();
            }
        }

        if (!currentTestDataGroup.isEmpty()) {
            groupedTests.add(new ArrayList<>(currentTestDataGroup));
        }

        model.addAttribute("groupedTests", groupedTests);
        model.addAttribute("patientId", patientId);

        return "testHistory";
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
        List<TestData> patientTestDataData = testDataService.getAllTestData().stream()
                .filter(testData -> {
                    Long testIdValue = testData.getTestId();
                    return testIdValue != null && testIdValue.equals(testId);
                })
                .collect( Collectors.toList());

        List<List<Object>> graphData = new ArrayList<>();

        for (TestData testData : patientTestDataData) {
            List<Object> dataPoint = new ArrayList<>();
            dataPoint.add(testData.getSecond());
            dataPoint.add(testData.getVolume());
            graphData.add(dataPoint);
        }

        graphData.sort( Comparator.comparingInt( o -> (int) o.get(0)));

        System.out.println("Graph data response: " + graphData);

        return graphData;
    }

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
        testData.setTestDate( LocalDate.now());
        testData.setRecordedAt( LocalDateTime.now());
        testData.setPatient(patient);
        testData.setDevice(device);
        testData.setSecond(second);
        testData.setVolume(volume);

        temporaryTestDataDataList.add(testData);

        if (temporaryTestDataDataList.size() == 6) {
            Long testId = testIdGenerator.getAndIncrement();
            for (TestData td : temporaryTestDataDataList) {
                td.setTestId(testId);
                testDataService.saveTestData(td);
            }
            temporaryTestDataDataList.clear();
            model.addAttribute("message", "TestData data saved successfully.");
            return "index";
        } else {
            model.addAttribute("message", "Waiting for more data. Current count: " + temporaryTestDataDataList.size());
            return "index";
    }


}

    @GetMapping("/api/patients/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return patientRepository.findById(id)
                .map(patient -> ResponseEntity.ok().body(patient))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}



