// On Selection change for onscreen tip and ask cobot form based on dropdown category selection
function OnSelectionChange (select) {
            var selectedOption = select.options[select.selectedIndex];
            var selection = selectedOption.value;
            var selectedSpan = document.getElementById ("onscreenTip");

            if (selection == "Find") {
              selectedSpan.innerHTML = "Please select a category";
              document.getElementById('where').style.display = "none";
              document.getElementById('who').style.display = "none";
              document.getElementById('what').style.display = "none";
              document.getElementById('location_result').style.display = "none";
              document.getElementById('people_result').style.display = "none";
              document.getElementById('event_name').style.display = "none";
            } else if (selection == "Where") {
              selectedSpan.innerHTML = "Find a person's location";
              document.getElementById('where').style.display = "block";
              document.getElementById('location_result').style.display = "block";
              document.getElementById('who').style.display = "none";
              document.getElementById('what').style.display = "none";
              document.getElementById('people_result').style.display = "none";
              document.getElementById('event_name').style.display = "none";
            } else if (selection == "Who") {
              selectedSpan.innerHTML = "Find a person's information";
              document.getElementById('who').style.display = "block";
              document.getElementById('people_result').style.display = "block";
              document.getElementById('where').style.display = "none";
              document.getElementById('what').style.display = "none";
              document.getElementById('location_result').style.display = "none";
              document.getElementById('event_name').style.display = "none";
            } else if (selection == "What") {
              selectedSpan.innerHTML = "Find an event's location";
              document.getElementById('what').style.display = "block";
              document.getElementById('event_name').style.display = "block";
              document.getElementById('where').style.display = "none";
              document.getElementById('who').style.display = "none";
              document.getElementById('location_result').style.display = "none";
              document.getElementById('people_result').style.display = "none";

            }
}
