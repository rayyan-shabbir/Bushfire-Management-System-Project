<%@ page import="com.google.gson.Gson" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Fire Reports</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.14.0/css/all.css"
          integrity="sha384-HzLeBuhoNPvSl5KYnjx0BT+WB0QEEqLprO+NBkkk5gbc67FTaL7XIGa2w1L0Xbgc"
          crossorigin="anonymous"/>
    <style>
        #map {
            width: 100%;
            height: 100vh;
            margin: 0;
            padding: 0;
        }
    </style>
</head>
<body>
    <div id="map"></div>

    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB41DRUbKWJHPxaFjMAwdrzWzbVKartNGg"></script>
    <script>

 // Function to add markers to the map
    function addMarkers(map, data, icon) {
        data.forEach(function(item) {
            const marker = new google.maps.Marker({
                position: { lat: parseFloat(item[1]), lng: parseFloat(item[2]) },
                icon: icon,
                draggable: true,
            });
            
            const popupContent = new google.maps.InfoWindow();
            
            google.maps.event.addListener(marker, 'click', (function(marker) {
              return function(){
                popupContent.setContent(item[0])
                popupContent.open(map, marker)
              }
            })(marker)
            )
            marker.setMap(map);

            /* const infoWindow = new google.maps.InfoWindow({
                content: item[0],
            });

            marker.addListener("click", function() {
                infoWindow.open(map, marker);
            }); */
        });
    }

    // Create the map
    let map = new google.maps.Map(document.getElementById("map"), {
        center: { lat: -26.853388, lng: 133.275154 },
        zoom: 8,
    });

    // Serialize Java variables into JSON using Gson
    const dronesData = <%= new Gson().toJson(request.getAttribute("dronesData")) %>;
    const trucksData = <%= new Gson().toJson(request.getAttribute("trucksData")) %>;
    const reportsData = <%= new Gson().toJson(request.getAttribute("reportsData")) %>;

    // Define marker icons for drones, trucks, and reports
    const droneIcon = {
        url: "images/drone.png", // Replace with the URL of your drone icon
        scaledSize: new google.maps.Size(40, 40), // Change the size as needed
    };

    const truckIcon = {
        url: "images/firetruck.png", // Replace with the URL of your truck icon
        scaledSize: new google.maps.Size(40, 40), // Change the size as needed
    };

    const reportIcon = {
        url: "images/fire.png", // Replace with the URL of your report icon
        scaledSize: new google.maps.Size(40, 40), // Change the size as needed
    };

    console.log(dronesData);
    console.log(trucksData);
    console.log(reportsData);
    // Add markers for drones, trucks, and reports
    addMarkers(map, dronesData, droneIcon);
    addMarkers(map, trucksData, truckIcon);
    addMarkers(map, reportsData, reportIcon);
    </script>
</body>
</html>
