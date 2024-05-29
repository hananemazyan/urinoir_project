## How does Bootstrap's testData suite work?

Bootstrap uses [Jasmine](https://jasmine.github.io/). Each plugin has a file dedicated to its testData in `testData/unit/<plugin-name>.spec.js`.

- `visual/` contains "visual" testData which are run interactively in real browsers and require manual verification by humans.

To run the unit testData suite via [Karma](https://karma-runner.github.io/), run `npm run js-testData`.
To run the unit testData suite via [Karma](https://karma-runner.github.io/) and debug, run `npm run js-debug`.

## How do I add a new unit testData?

1. Locate and open the file dedicated to the plugin which you need to add testData to (`testData/unit/<plugin-name>.spec.js`).
2. Review the [Jasmine API Documentation](https://jasmine.github.io/pages/docs_home.html) and use the existing testData as references for how to structure your new testData.
3. Write the necessary unit testData(s) for the new or revised functionality.
4. Run `npm run js-testData` to see the results of your newly-added testData(s).

**Note:** Your new unit testData should fail before your changes are applied to the plugin, and should pass after your changes are applied to the plugin.

## What should a unit testData look like?

- Each testData should have a unique name clearly stating what unit is being tested.
- Each testData should be in the corresponding `describe`.
- Each testData should testData only one unit per testData, although one testData can include several assertions. Create multiple testData for multiple units of functionality.
- Each testData should use [`expect`](https://jasmine.github.io/api/edge/matchers.html) to ensure something is expected.
- Each testData should follow the project's [JavaScript Code Guidelines](https://github.com/twbs/bootstrap/blob/main/.github/CONTRIBUTING.md#js)

## Code coverage

Currently we're aiming for at least 90% testData coverage for our code. To ensure your changes meet or exceed this limit, run `npm run js-testData-karma` and open the file in `js/coverage/lcov-report/index.html` to see the code coverage for each plugin. See more details when you select a plugin and ensure your change is fully covered by unit testData.

### Example testData

```js
// Synchronous testData
describe('getInstance', () => {
  it('should return null if there is no instance', () => {
    // Make assertion
    expect(Tab.getInstance(fixtureEl)).toBeNull()
  })

  it('should return this instance', () => {
    fixtureEl.innerHTML = '<div></div>'

    const divEl = fixtureEl.querySelector('div')
    const tab = new Tab(divEl)

    // Make assertion
    expect(Tab.getInstance(divEl)).toEqual(tab)
  })
})

// Asynchronous testData
it('should show a tooltip without the animation', () => {
  return new Promise(resolve => {
    fixtureEl.innerHTML = '<a href="#" rel="tooltip" title="Another tooltip"></a>'

    const tooltipEl = fixtureEl.querySelector('a')
    const tooltip = new Tooltip(tooltipEl, {
      animation: false
    })

    tooltipEl.addEventListener('shown.bs.tooltip', () => {
      const tip = document.querySelector('.tooltip')

      expect(tip).not.toBeNull()
      expect(tip.classList.contains('fade')).toEqual(false)
      resolve()
    })

    tooltip.show()
  })
})
```
