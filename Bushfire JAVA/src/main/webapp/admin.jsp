<%  
	if(session.getAttribute("name") == null) {
		response.sendRedirect("login.jsp");
	}
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <style>.mySlides {display:none;}</style>
    <title>Welcome - Admin</title>
    <link rel="stylesheet" href="css/admin-styles.css" />
    <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.14.0/css/all.css"
      integrity="sha384-HzLeBuhoNPvSl5KYnjx0BT+WB0QEEqLprO+NBkkk5gbc67FTaL7XIGa2w1L0Xbgc"
      crossorigin="anonymous"/>
  </head>

  <body>
    <!-- Navbar Section -->
    <nav class="navbar">
      <div class="navbar__container">
        <a href="#home" id="navbar__logo">Admin Dashboard</a>
        <div class="navbar__toggle" id="mobile-menu">
          <span class="bar"></span> <span class="bar"></span>
          <span class="bar"></span>
        </div>
        <ul class="navbar__menu">
          <li class="navbar__item">
            <a href="#home" class="navbar__links" id="home-page">Home</a>
          </li>
          <li class="navbar__item">
            <a href="#about" class="navbar__links" id="about-page">Reports</a>
          </li>
          <li class="navbar__item">
            <a href="#services" class="navbar__links" id="services-page"
              >Forests</a>
          </li>
          <li class="navbar__item">
            <a href="#editdrone" class="navbar__links" id="services-page"
              >Edit Drone</a>
          </li>
          <li class="navbar__btn">
            <a href="Logout" class="button" id="signup">Logout</a>
          </li>
        </ul>
      </div>
    </nav>

    <div class="slider" id="home">
      <div class="list">
          <div class="item">
              <img src="images/a1.png" alt="">
          </div>
          <div class="item">
              <img src="images/a2.png" alt="">
          </div>
          <div class="item">
              <img src="images/a3.png" alt="">
          </div>
          <div class="item">
              <img src="images/a4.png" alt="">
          </div>
          <div class="item">
              <img src="images/a5.png" alt="">
          </div>
      </div>
      
      <div class="buttons">
          <button id="prev"><</button>
          <button id="next">></button>
      </div>
      <ul class="dots"> 
        <br>
          <li class="active"></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
      </ul>
  </div>
  <script>

let slider = document.querySelector('.slider .list');
let items = document.querySelectorAll('.slider .list .item');
let next = document.getElementById('next');
let prev = document.getElementById('prev');
let dots = document.querySelectorAll('.slider .dots li');

let lengthItems = items.length - 1;
let active = 0;
next.onclick = function(){
    active = active + 1 <= lengthItems ? active + 1 : 0;
    reloadSlider();
}
prev.onclick = function(){
    active = active - 1 >= 0 ? active - 1 : lengthItems;
    reloadSlider();
}
let refreshInterval = setInterval(()=> {next.click()}, 3000);
function reloadSlider(){
    slider.style.left = -items[active].offsetLeft + 'px';
    // 
    let last_active_dot = document.querySelector('.slider .dots li.active');
    last_active_dot.classList.remove('active');
    dots[active].classList.add('active');

    clearInterval(refreshInterval);
    refreshInterval = setInterval(()=> {next.click()}, 3000);

    
}

dots.forEach((li, key) => {
    li.addEventListener('click', ()=>{
         active = key;
         reloadSlider();
    })
})
window.onresize = function(event) {
    reloadSlider();
};
  </script>
    <!-- Hero Section -->
    <!-- <div class="hero" id="home">
      <div class="hero__container">
        <h1 class="hero__heading">Bushfire Management System</h1>
        <p class="hero__description" font face="Allura">Bushfire Management System</p> -->
      <!-- </div>
    </div> -->

    <!-- About Section -->
    <div class="main" id="about">
      <div class="main__container">
        <div class="main__img--container">
          <div class="main__img--card"><img src="images/bg6.png"></div>

        </div>
        <div class="main__content">
          <h1>
            Fire Reports
          </h1>
        <p> 
          Location of Drones, Fire Trucks and Fire <br>is available on Maps.
        </p>
        <button class="main__btn"><a href="map">View Reports</a></button>

        </div>
      </div>
    </div>

    <!-- Services Section -->
    <div class="container" id="services">
      <h1>Forests in Australia</h1>
          <div class="row">
              <div class="service">
                <img src="images/1.png" class="image-example">
                  <h2>Acacia</h2>
                  <p>Arid-region trees with compound leaves, comprising over 1,000 species.</p>
              </div>
              <div class="service">
                  <!-- <i class="fa-brands fa-js-square"></i>  -->
                  <img src="images/2.png" class="image-example">
                  <h2>Callitris</h2>
                  <p>Coniferous cypresses native to Australia and nearby regions.</p>
              </div>
              <div class="service">
                  <!-- <i class="fa-brands fa-html5"></i> -->
                  <img src="images/3.png" class="image-example">
                  <h2>Casuarina</h2>
                  <p>Sheoaks with slender branchlets and needle-like leaves.</p>
              </div>
              <div class="service">
                  <!-- <i class="fa-brands fa-css3"></i>  -->
                  <img src="images/4.png" class="image-example">
                  <h2>Eucalypt</h2>
                  <p>Aromatic Australian trees known for distinctive fruit capsules.</p>
              </div>
              <div class="service">
                  <!-- <i class="fa-brands fa-react"></i>  -->
                  <img src="images/5.png" class="image-example">
                  <h2>Mangrove</h2>
                  <p> Salt-tolerant trees vital for coastal ecosystems.</p>
              </div>
              <div class="service">
                  <!-- <i class="fa-brands fa-node"></i>  -->
                  <img src="images/6.png" class="image-example">
                  <h2>Melaleuca</h2>
                  <p>Aromatic trees with papery bark, some yielding tea tree oil.</p>
              </div>
              <div class="service">
                  <!-- <i class="fa-brands fa-php"></i> -->
                  <img src="images/7.png" class="image-example">
                  <h2>Rainforest</h2>
                  <p>Biodiverse forests with dense canopies in tropical regions.</p>
              </div>
              <div class="service">
                  <!-- <i class="fa-solid fa-database"></i>  -->
                  <img src="images/8.png" class="image-example">
                  <h2>Industrial Plantation</h2>
                  <p>Managed tree farms for commercial timber production.</p>
              </div>
          </div>
     </div>

    <!-- Footer Section -->
    <footer class="footer-distributed">
      <div class="footer-left">

        <h3>Bushfire Management System</h3>
     
        <p class="footer-links">
          <a href="#home" class="link-1">Home</a>
    
          <a href="#about">Reports</a>
    
          <a href="#services">Forests</a>
    
        </p>
    
        <p class="footer-company-name">Bushfire Management System © 2024</p>
      </div>
    
      <div class="footer-center">
    
        <div>
          <i class="fa fa-map-marker"></i>
          <p><span>444 S. Cedros Ave</span> Solana Beach, California</p>
        </div>
    
        <div>
          <i class="fa fa-phone"></i>
          <p>+1.555.555.5555</p>
        </div>
    
        <div>
          <i class="fa fa-envelope"></i>
          <p><a href="mailto:support@company.com">support@company.com</a></p>
        </div>
    
      </div>
    
      <div class="footer-right">
    
        <p style="color: white;" class="footer-company-about">
          <span>About the company</span>
          We provide services for saving forests in Australia from enormous fires. Through research and experiences, we've owned skills in fire management and detection.
        </p>
    
    
      </div>
    </footer>

    <script src="js/app.js"></script>
  </body>
</html>