<%@ attribute name="articleName" required="true" type="java.lang.String" %>
<%@ attribute name="articleID" required="true" type="java.lang.String"%>
<%@ attribute name="articlePrice" required="true" type="java.lang.Double" %>
<div id="article${articleID}" class="articleTeaser white">
    <img src="/shop/image?articleID=${articleID}&teaser=true" class="centeredImage"/>
    <div class="teaserName">${articleName}</div>
    <div class="priceLabel right">&euro; ${articlePrice}</div>
    <img src="/png/check-marg.png"/>
    <button type="submit" class="right">Hinzufuegen</button>
</div>