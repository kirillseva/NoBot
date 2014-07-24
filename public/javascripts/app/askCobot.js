// On Selection change for onscreen tip based on dropdown selection
function OnSelectionChange (select) {
            var selectedOption = select.options[select.selectedIndex];
            var selection = selectedOption.value;
            var selectedSpan = document.getElementById ("onscreenTip");

            if (selection == "Find") {
              selectedSpan.innerHTML = "Please select a category";
            } else if (selection == "Where") {
              selectedSpan.innerHTML = "Find a person's location";
            } else if (selection == "Who") {
              selectedSpan.innerHTML = "Find a person's information";
            } else if (selection == "What") {
              selectedSpan.innerHTML = "Find an event's location";
            }
}
