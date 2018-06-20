// document.getElementById('registerStart').onclick = function() {
//     document.getElementById('registerTable').style.visibility = 'visible';
//     document.getElementById('registerStart').style.visibility = 'hidden';
// };





function revealLightbox(evt) {
    var lightbox = evt.target.parentElement.getElementsByClassName('lightbox')[0];
    if (lightbox != null) {
        lightbox.style.display = "block";
    }
}

function hideLightbox(evt) {
    var lightbox = evt.target.parentElement.getElementsByClassName('lightbox')[0];
    if (lightbox != null) {
        lightbox.style.display = "none";
    }
}

function displayRegisterForm() {
    var pitchForm = document.getElementById("registerPitch");
    pitchForm.style.display = "none";
    var registerForm = document.getElementById("registerForm");
    registerForm.style.display = "block";
}

window.onload = function() {
    if (document.title === "Home") {
        var statusIcons = document.querySelectorAll("[id^='statusIcon']");
        for (var i = 0; i < statusIcons.length; i++) {
            statusIcons[i].addEventListener('mouseenter', revealLightbox);
            statusIcons[i].addEventListener('mouseleave', hideLightbox)
        }

        var logoutButton = document.getElementById("logoutButton");
    }

    else if(document.title === "Login/Registrierung") {
        var beginRegisterButton = document.getElementById("beginRegisterButton");
        beginRegisterButton.addEventListener("click", displayRegisterForm)
    }

}