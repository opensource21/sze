
<%@ page import="net.sf.sze.zeugnis.Bewertung" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Schul-Zeugnis-Erstellung  - Bewertungen</title>
    </head>
    <body>
        <div class="nav">
          <g:form method="post" useToken="true" name="chooseSchulfach">
                <g:select  name="schulfachId" from="${schulfaecher}"  value="${schulfachId}" 
                    optionKey="id" noSelection="[0:'Alle anzeigen']" 
                    onchange="getElementById('chooseSchulfach').submit(action='filterBewertungen')"/>
                <span class="button"><g:actionSubmit class="update" action="filterBewertungen" value="Schulfach filtern" id="chooseSchulfachBtn" /></span>
          </g:form>
          <g:javascript>
          if(document.getElementById("chooseSchulfachBtn"))
            document.getElementById("chooseSchulfachBtn").style.display = "none";
          </g:javascript>          
                    
        </div>
        <div class="body">
            <h1>Bewertung für Klasse <b>${klasse}</b> im Schulhalbjahr <b>${schulhalbjahr}</b></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                               <g:sortableColumn property="zeugnis.schueler.name" title="Zeugnis" />
                               <g:sortableColumn property="schulfach" title="Schulfach" />                          
                               <g:sortableColumn property="leistungsniveau" title="Leistungsniveau" />                       
                               <g:sortableColumn property="note" title="Note" />                        
                               <g:sortableColumn property="sonderNote" title="Sonder Note" />
                               <g:sortableColumn property="leistungNurSchwachAusreichend" title="Schwach ausreichend" />                        
                               <g:sortableColumn property="relevant" title="Relevant" />                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${bewertungInstanceList}" status="i" var="bewertungInstance">
                        <g:if test="${bewertungInstance.id==lastEditedBewertung}">
                            <tr class="lastEdited">
                        </g:if>
                        <g:else>
                            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        </g:else>                                               
                            <td><g:link action="editBewertung" id="${bewertungInstance.id}">${fieldValue(bean:bewertungInstance, field:'zeugnis')}</g:link></td>
                            
                            <td><g:link action="editBewertung" id="${bewertungInstance.id}">${fieldValue(bean:bewertungInstance, field:'schulfach')}</g:link></td>
                        
                            <td><g:link action="editBewertung" id="${bewertungInstance.id}">${fieldValue(bean:bewertungInstance, field:'leistungsniveau')}</g:link></td>
                        
                            <td><g:link action="editBewertung" id="${bewertungInstance.id}">${fieldValue(bean:bewertungInstance, field:'note')}</g:link></td>
                        
                            <td><g:link action="editBewertung" id="${bewertungInstance.id}">${fieldValue(bean:bewertungInstance, field:'sonderNote')}</g:link></td>

                            <td><g:link action="editBewertung" id="${bewertungInstance.id}"><g:formatBoolean boolean="${bewertungInstance.leistungNurSchwachAusreichend}"/></g:link></td>
                        
                            <td><g:link action="editBewertung" id="${bewertungInstance.id}"><g:formatBoolean boolean="${bewertungInstance.relevant}"/></g:link></td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate params="${params.findAll{ k,v -> k != 'filter' && k!= 'offset'}}" total="${bewertungInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
