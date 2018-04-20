<%@ page import="cc.moecraft.web.hydomainmapperv2.Main" %>
<%@ page import="cc.moecraft.web.hydomainmapperv2.MappingRule" %>
<%@ page import="cc.moecraft.web.hydomainmapperv2.MappingMethod" %><%--
 * 此JSP由 Hykilpikonna 在 2018/04/19 21:19 创建!
 * Created by Hykilpikonna on 2018/04/19 21:19!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    System.out.println("ServerName = " + request.getServerName());

    String domain = request.getServerName().toLowerCase()
            .replaceAll("http.*://", "")
            .replaceAll("/.*", "")
            .replace(".", "`");

    MappingRule mappingRule = Main.mappingConfig().getMappingRule(domain);

    if (mappingRule.equals(MappingRule.MAPPING_RULE_NOT_EXIST) || mappingRule.getMethod() == null)
    {
%>
<meta http-equiv="Refresh" content="0; url=/domain-404.jsp?d=<%=domain.replace("`", ".")%>">
<%
        return;
    }

    if (mappingRule.getMethod() == MappingMethod.REDIRECT)
    {
%>
<meta http-equiv="Refresh" content="0; url=<%=mappingRule.getTo()%>">
<%
    }
    else if (mappingRule.getMethod() == MappingMethod.AJAX)
    {
%>
<script>
    window.onload = load;

    function load()
    {
        var xmlHttpRequest = new XMLHttpRequest();

        xmlHttpRequest.onreadystatechange = function()
        {
            if (this.readyState === 4 && this.status === 200)
            {
                document.getElementById("anidthatcantbepurposefullynamedforanythingforthecsses").innerHTML = this.responseText;
            }
        };
        xmlHttpRequest.open("GET", "<%=mappingRule.getTo()%>", true);
        xmlHttpRequest.send();
    }
</script>
<html id="anidthatcantbepurposefullynamedforanythingforthecsses"></html>
<%
    }
    else if (mappingRule.getMethod() == MappingMethod.CORS)
    {
%>
<script>
    window.onload = makeCorsRequest();

    // Create the XHR object.
    function createCORSRequest(method, url)
    {
        var xhr = new XMLHttpRequest();
        if ("withCredentials" in xhr)
        {
            // XHR for Chrome/Firefox/Opera/Safari.
            xhr.open(method, url, true);
        }
        else if (typeof XDomainRequest != "undefined")
        {
            // XDomainRequest for IE.
            xhr = new XDomainRequest();
            xhr.open(method, url);
        }
        else
        {
            // CORS not supported.
            xhr = null;
        }
        return xhr;
    }

    // Helper method to parse the title tag from the response.
    function getTitle(text)
    {
        return text.match('<title>(.*)?</title>')[1];
    }

    // Make the actual CORS request.
    function makeCorsRequest()
    {
        // This is a sample server that supports CORS.
        var url = '<%=mappingRule.getTo()%>';

        var xhr = createCORSRequest('GET', url);
        if (!xhr)
        {
            alert('CORS not supported');
            return;
        }

        // Response handlers.
        xhr.onload = function()
        {
            var text = xhr.responseText;
            var title = getTitle(text);
            document.getElementById("anidthatcantbepurposefullynamedforanythingforthecsses").innerHTML = text;
        };

        xhr.onerror = function()
        {
            alert('Request Error');
        };

        xhr.send();
    }
</script>
<html id="anidthatcantbepurposefullynamedforanythingforthecsses"></html>
<%
    }
%>