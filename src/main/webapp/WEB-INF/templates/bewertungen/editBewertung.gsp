
<%@ page import="net.sf.sze.zeugnis.Bewertung" %>
<%@ page import="net.sf.sze.zeugnis.BinnenDifferenzierteBewertung" %>
<%@ page import="net.sf.sze.zeugnis.AussenDifferenzierteBewertung" %>
instanceof T(String) 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Bearbeite Bewertung</title>
    </head>
    <body onload="setFocus('note')">
        <div class="body">
            <%schulhalbjahr=bewertungInstance?.zeugnis?.schulhalbjahr %>
            <h1>Bearbeite Bewertung im Fach <b>${bewertungInstance?.schulfach}</b> für  <b>${bewertungInstance?.zeugnis?.schueler}</b> (Klasse ${bewertungInstance?.zeugnis?.klasse.calculateKlassenname(schulhalbjahr.jahr)} im Hj: ${schulhalbjahr}) </h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${bewertungInstance}">
            <div class="errors">
                <g:renderErrors bean="${bewertungInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${bewertungInstance?.id}" />
                <input type="hidden" name="version" value="${bewertungInstance?.version}" />
                <input type="hidden" name="prevId" value="${prevId}" />
                <input type="hidden" name="nextId" value="${nextId}" />
                <div class="dialog">
                    <table>
                        <tbody>
                            <g:if test="${bewertungInstance instanceof BinnenDifferenzierteBewertung || bewertungInstance instanceof AussenDifferenzierteBewertung}">  
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="leistungsniveau">Leistungsniveau:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:bewertungInstance,field:'leistungsniveau','errors')}">
                                    <g:select id="leistungsniveau" name="leistungsniveau" from="${bewertungInstance.constraints.leistungsniveau.inList}" value="${bewertungInstance.leistungsniveau}" ></g:select>
                                </td>
                            </tr> 
                            </g:if>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="note">Note:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:bewertungInstance,field:'note','errors')}">
                                    <g:select from="${1..6}" id="note" name="note" value="${bewertungInstance?.note}" noSelection="['':'']"></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="sonderNote">Sonder Note:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:bewertungInstance,field:'sonderNote','errors')}">
                                    <input type="text" id="sonderNote" name="sonderNote" value="${fieldValue(bean:bewertungInstance,field:'sonderNote')}"/>
                                </td>
                            </tr> 
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="leistungNurSchwachAusreichend">Leistung nur schwach ausreichend:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:bewertungInstance,field:'leistungNurSchwachAusreichend','errors')}">
                                    <g:checkBox name="leistungNurSchwachAusreichend" value="${bewertungInstance?.leistungNurSchwachAusreichend}" ></g:checkBox>
                                </td>
                            </tr>                         
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="relevant">Relevant:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:bewertungInstance,field:'relevant','errors')}">
                                    <g:checkBox name="relevant" value="${bewertungInstance?.relevant}" ></g:checkBox>
                                </td>
                            </tr> 
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <g:if test="${prevId}">
                        <span class="button"><g:actionSubmit class="saveAndPrevious" action="updateBewertungAndEditPrevious" value="Speichern und Zurück" /></span>
                    </g:if>
                    <span class="button"><g:actionSubmit class="save" action="updateBewertung" value="Speichern" /></span>
                    <span class="button"><g:actionSubmit class="cancel" action="cancelBewertung"  value="Abbrechen" /></span>
                    <g:if test="${nextId}">
                        <span class="button"><g:actionSubmit class="saveAndNext" action="updateBewertungAndEditNext" value="Speichern und Weiter" /></span>
                    </g:if>
                </div>
            </g:form>
        </div>
    </body>
</html>
