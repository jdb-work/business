<%@page import="bzsvc.CustomerManager"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<portlet:defineObjects />
<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="//cdn.jsdelivr.net/jquery.xml2json/1.2/jquery.xml2json.js"></script>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet"/>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/lodash.js/2.4.1/lodash.min.js"></script>
<form name="aform" id="buttonform" action='#' method="post">
    <input type="text" size="30" id="customerId" name="customerId">
    <button type="button" id="submitButton" onclick="call()">Search</button>
</form>
<br>

<div id="error"></div>
<div id="street1"></div>
<div id="street2"></div>
<div id="street3"></div>
<div id="street4"></div>
<div id="city"></div>
<div id="country"></div>
<div id="county"></div>
<div id="name"></div>
<div id="secondary"></div>
<div id="zip"></div>
<div id="state"></div>
<script>
    function processResponse(xml) {
        $("#error").html('');
        var json = jQuery.xml2json(xml);
        if (json.error != undefined) {
            $("#error").html('<p class="text-danger">' + json.error + '</p>');
        }
        $("#secondary").html(json.address.mailingNameSecondary);
        $("#street1").html(json.address.addressLine1);
        $("#street2").html(json.address.addressLine2);
        $("#street3").html(json.address.addressLine3);
        $("#street4").html(json.address.addressLine4);
        $("#country").html(json.address.countryCode);
        $("#county").html(json.address.countyCode);
        $("#name").html(json.address.mailingName);
        $("#state").html(json.address.stateCode);
        $("#zip").html(json.address.postalCode);
        $("#city").html(json.address.city);
    }
    function call() {
        var customerId = document.aform.customerId.value;
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>/customer',
            data: 'id=' + customerId,
            success: function(data) {
                processResponse(data);
            }
        });
    }
</script>