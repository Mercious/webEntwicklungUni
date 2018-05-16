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
window.onload = function() {
    var statusIcons = document.querySelectorAll("[id^='statusIcon']");
    for (var i = 0; i < statusIcons.length; i++) {
        statusIcons[i].addEventListener('mouseenter', revealLightbox);
        statusIcons[i].addEventListener('mouseleave', hideLightbox)
    }
}