

// Autor: Serkan Altay
function revealLightbox(evt) {
    var lightbox = evt.target.parentElement.getElementsByClassName('lightbox')[0];
    if (lightbox != null) {
        lightbox.style.display = "block";
    }
}
// Autor: Serkan Altay
function hideLightbox(evt) {
    var lightbox = evt.target.parentElement.getElementsByClassName('lightbox')[0];
    if (lightbox != null) {
        lightbox.style.display = "none";
    }
}

// Autor: Serkan Altay
function displayRegisterForm() {
    var pitchForm = document.getElementById("registerPitch");
    pitchForm.style.display = "none";
    var registerForm = document.getElementById("registerForm");
    registerForm.style.display = "block";
}

// Autor: Felix Hartmann
function showPossibleItemList(evt) {
    var configSlot = evt.target.parentElement.parentElement;
    var itemType = configSlot.getAttribute("data-type");
    var ajaxRequest = new XMLHttpRequest();
    ajaxRequest.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
           var articleList = JSON.parse(this.responseText);
           var articleElement = document.getElementById("articleList");
           articleElement.innerHTML = ''; // clear it of previous items
           for (var i = 0; i < articleList.length; i++) {
               var newEntry = document.createElement("div");
               newEntry.classList.add("articleListItem");
               newEntry.appendChild(document.createTextNode(articleList[i].articleName));
               var priceEntry = document.createElement("div");
               priceEntry.appendChild(document.createTextNode("€ " + articleList[i].price));
               priceEntry.classList.add("right", "boldText", "green");
               newEntry.appendChild(priceEntry);
               newEntry.setAttribute("data-articleID", articleList[i].articleID);
               newEntry.setAttribute("data-type", itemType);

               var imageIcon = document.createElement("img");
               imageIcon.setAttribute("src", "WebContent/png/show_image_icon.png");
               imageIcon.classList.add("showImageIcon", "right");
               imageIcon.addEventListener("mouseover", showArticleImage);
               imageIcon.addEventListener("mouseleave", hideArticleImage);
               newEntry.appendChild(imageIcon);

               var articleImage = document.createElement("img");
               articleImage.classList.add("modal-content-image");
               articleImage.setAttribute("src", "/shop/image?articleID=" + articleList[i].articleID +"&teaser=false");
               articleImage.setAttribute("id", "article" + articleList[i].articleID);
               newEntry.appendChild(articleImage);
               // We use function closures here, because this function needs to access information about the articles that we have here
               // to avoid known issues when doing that in loops, we use a function factory to make sure each function works on their own
               // copy of values
               newEntry.addEventListener("click", createAddItemFunction(articleList[i].articleID, articleList[i].articleName, articleList[i].price, itemType));

               articleElement.appendChild(newEntry);
            }

            var articleListModal = document.getElementById("articleListModal");
            articleListModal.style = "display:block";
            articleListModal.getElementsByClassName("modal-close")[0].addEventListener("click", hidePossibleItemList);
        }
    };

    var requestUrl = "articles/articlesOfType" + "?type=" + itemType + "&onlyCompatible=true";
    ajaxRequest.open("GET", requestUrl, true);
    ajaxRequest.send();


}

// Autor: Felix Hartmann
function hidePossibleItemList() {
    document.getElementById("articleListModal").style = "display:none";
}

// Autor: Felix Hartmann
function showArticleImage(evt) {
    var articleID = evt.target.parentElement.getAttribute("data-articleID");
    var articleImageModal = document.getElementById("article" + articleID);
    articleImageModal.style = "display:inline-block";

}

// Autor: Felix Hartmann
function createAddItemFunction(articleID, articleName, price, itemType) {
    return function() {
        var addItemArea = document.getElementById("addItemArea" + itemType);
        var itemDetailsArea = document.getElementById("itemDetailsArea" + itemType);
        // hide the plus-icon, since the slot is now filled
        addItemArea.classList.add("hide");
        itemDetailsArea.getElementsByClassName("centeredImage")[0].setAttribute("src", "/shop/image?articleID=" + articleID + "&teaser=true");
        itemDetailsArea.getElementsByClassName("teaserName")[0].innerHTML = articleName;
        itemDetailsArea.getElementsByClassName("priceLabel")[0].innerHTML = "€ " + price;
        itemDetailsArea.classList.remove("hide");
        itemDetailsArea.getElementsByClassName("deleteIcon")[0].setAttribute("id", "deleteIcon" + articleID);
        var priceLabel = document.getElementById("totalPrice").getElementsByClassName("priceLabel")[0];
        var newPrice = parseFloat(priceLabel.innerHTML.replace("€ ", "")) + price;
        var newPriceString = "€ " + newPrice;
        priceLabel.innerHTML = newPriceString.substring(0, newPriceString.indexOf('.') + 3);

        document.getElementById("articleListModal").style = "display:none";
        var ajaxRequest = new XMLHttpRequest();
        var requestUrl = "configurator" + "?action=add" + "&articleID=" + articleID;
        ajaxRequest.open("POST", requestUrl, true);
        ajaxRequest.send();
    }
}

// Autor: Felix Hartmann
function addEventListenerToDeleteIcon() {
    var allDeleteIcons = document.querySelectorAll('*[id^="deleteIcon"]');
    for (var i = 0; i < allDeleteIcons.length; i++) {
        allDeleteIcons[i].addEventListener("click", function(evt) {
           var contentSlot = evt.target.parentElement.parentElement;
           var itemType = contentSlot.getAttribute("data-type");
           document.getElementById("itemDetailsArea" + itemType).classList.add("hide");
           document.getElementById("addItemArea" + itemType).classList.remove("hide");
           var priceLabel = document.getElementById("totalPrice").getElementsByClassName("priceLabel")[0];
           var articlePrice = parseFloat(evt.target.parentElement.getElementsByClassName("priceLabel")[0].innerHTML.replace("€ ", ""));
           var newPrice = parseFloat(priceLabel.innerHTML.replace("€ ", "")) - articlePrice;
           var newPriceString = "€ ";
           if (newPrice < 0)
               newPriceString += "0.00";
           else
               newPriceString += newPrice;
           priceLabel.innerHTML = newPriceString.substring(0, newPriceString.indexOf('.') + 3);
           var ajaxRequest = new XMLHttpRequest();
           var requestUrl = "configurator" + "?action=remove" + "&type=" + itemType;
           ajaxRequest.open("POST", requestUrl, true);
           ajaxRequest.send();
        });
    }
}

// Autor: Felix Hartmann
function hideArticleImage(evt) {
    var articleID = evt.target.parentElement.getAttribute("data-articleID");
    var articleImageModal = document.getElementById("article" + articleID);
    articleImageModal.style = "display:none";

}

// Autor: Serkan Altay
function addNewArticle(evt) {

    document.getElementById("articleID").removeAttribute("readonly");
    document.getElementById("category").style = "display: none";
    var newInputSelect = document.createElement("select");
    newInputSelect.setAttribute("name", "category");
    newInputSelect.setAttribute("id", "category");

    var defaultOption = document.createElement("option");
    defaultOption.innerHTML = "Bitte auswählen";
    defaultOption.setAttribute("selected", "")
    newInputSelect.appendChild(defaultOption);

    var categoryValueDivs = document.getElementById("categoryValues").getElementsByTagName("div");
    for (var i = 0; i < categoryValueDivs.length; i++) {
        var newOption = document.createElement("option");
        newOption.innerHTML = categoryValueDivs[i].innerHTML;
        newInputSelect.appendChild(newOption);
    }

    // remove the current picture, new article has none - the if-condition is to catch multiple click on the button
    var currentPictureElement = document.getElementById("currentPicture");
    if (currentPictureElement !== null && !currentPictureElement.classList.contains("hide")) {
        currentPictureElement.classList.add("hide");
    }

    var currentPictureLabel = document.getElementById("currentPictureLabel");
    if (currentPictureLabel !== null && !currentPictureLabel.classList.contains("hide")) {
        currentPictureLabel.classList.add("hide");
    }

    // clear the current inputs
    var allInputs = document.getElementsByTagName("input");
    for (var k = 0; k < allInputs.length; k++) {
        if (allInputs[k].getAttribute("type") === "text" || allInputs[k].getAttribute("type") === "number") {
            allInputs[k].setAttribute("value", "")
        }
    }

    var articleIDSelect = document.getElementById("articleIDList");
    var currentOptions = articleIDSelect.getElementsByTagName("option");
    for (count = 0; count < currentOptions.length; count++) {
        currentOptions[count].removeAttribute("selected");
    }

    var newDummyOption = document.createElement("option");
    newDummyOption.setAttribute("selected", "");
    newDummyOption.setAttribute("disabled", "");
    newDummyOption.innerHTML = "Neuer Artikel";

    articleIDSelect.appendChild(newDummyOption);


    var adminAreaForm = document.getElementById("adminAreaForm");
    var articleNameElement = document.getElementById("articleNameLabel");
    adminAreaForm.insertBefore(newInputSelect, articleNameElement);

}

// Autor: Serkan Altay (Grundgerüst, kleine Anpassunngen durch Felix Hartmann)
window.onload = function() {
    if (document.title === "Home") {
        var logoutButton = document.getElementById("logoutButton");

    }

    if (document.title === "Home" || document.title === "Suche") {
        var statusIcons = document.querySelectorAll("[id^='statusIcon']");
        for (var i = 0; i < statusIcons.length; i++) {
            statusIcons[i].addEventListener('mouseenter', revealLightbox);
            statusIcons[i].addEventListener('mouseleave', hideLightbox)
        }


        var addButtons = document.querySelectorAll("[id^='addButton']");
        for (var k = 0; k < addButtons.length; k++) {
            addButtons[k].addEventListener("click", function(evt) {
                var articleID = evt.target.parentElement.getAttribute("data-articleID");
                var ajaxRequest = new XMLHttpRequest();
                var requestUrl = "configurator" + "?action=add" + "&articleID=" + articleID;
                ajaxRequest.open("POST", requestUrl, true);
                ajaxRequest.send();
                window.location.href = "/shop/configurator";
            });
        }
    }

    else if(document.title === "Login/Registrierung") {
        var beginRegisterButton = document.getElementById("beginRegisterButton");
        beginRegisterButton.addEventListener("click", displayRegisterForm)
    }

    else if(document.title === "Meine Konfiguration") {
        var addIcons = document.getElementsByClassName("configAddPicture");
        for (var i = 0; i < addIcons.length; i++) {
            addIcons[i].addEventListener('click', showPossibleItemList);
        }

        addEventListenerToDeleteIcon();
    }

    else if(document.title === "Admin Area") {
        var newArticleIcon = document.getElementById("newArticleButton");
        newArticleIcon.addEventListener("click", addNewArticle);
    }

}