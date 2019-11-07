<!DOCTYPE html>
<html xmlns:g="http://www.w3.org/1999/html">
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'uver.label', default: 'Uver')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-uver" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-uver" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.uver}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.uver}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>

            <g:form resource="${this.uver}" method="POST">
                <fieldset class="form">
                    <f:all bean="uver"/>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>




<!--
            <g:form resource="${this.uver}" method="POST">
                <fieldset class="form">
                    <div class='fieldcontain required'>
                        <label for="nazevUveru">Název úvěru:</label>
                        <g:field name="nazevUveru" value="${uver.nazevUveru}" required="" type="text" /> <br>
                    </div>
                    <div class='fieldcontain required'>
                        <label for="platnostOd">Platnost OD:</label>
                        <g:datePicker name="platnostOd" value="${uver.platnostOd}" noSelection="['':'-vyber-']" precision="day" required="" /> <br>
                    </div>
                    <div class='fieldcontain required'>
                        <label for="delkaRoku">Délka roku:</label>
                        <g:field  name="delkaRoku" type="number" value="${uver.delkaRoku}" title="360 nebo 365" required="" /> <br>
                    </div>
                    <div class='fieldcontain required'>
                        <label for="maxUverovyRamec">Max. úvěrový rámec:</label>
                        <g:field name="maxUverovyRamec" value="${uver.maxUverovyRamec}" required="" type="number" /> <br>
                    </div>

                    <div class='fieldcontain required'>
                        <label for="urokovaSazba">Úroková sazba (list):</label>
                        <g:field name="urokovaSazba" min="1" max="100" value="${uver.urokovaSazba}" required="" type="number" step="0.01" /> %%%<br>
                    </div>

                    <div class='fieldcontain'>
                        <label for="mimoradnaSplatka">Mimořádná spátka:</label>
                        <g:field name="mimoradnaSplatka" value="${uver.mimoradnaSplatka}" type="checkbox" /> <br>
                    </div>
                    <div class='fieldcontain'>
                        <label for="predcasneUkonceni">Předčasné ukončení:</label>
                        <g:field name="predcasneUkonceni" value="${uver.predcasneUkonceni}" type="checkbox" /> <br>
                    </div>
                    <div class='fieldcontain'>
                        <label for="poznamka">Poznámka:</label>
                        <g:field name="poznamka" value="${uver.poznamka}" />
                    </div>
                </fieldset>

                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
-->


<!--
            <g:form resource="${this.uver}" method="POST">
                <fieldset class="form">
                    <f:all bean="uver"/>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
-->
        </div>
    </body>
</html>
