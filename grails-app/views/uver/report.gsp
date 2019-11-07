<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'uver.label', default: 'Uver')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>

        <a href="#list-uver" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>

        <div id="list-detail0" class="content scaffold-list" role="main">
            <h1>Detaily</h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:if test="${details}">
                <g:render template="reportDetail" model="[details: details]" />
            </g:if>



            <div class="pagination">
                <g:paginate total="${detailCount ?: 0}" />
            </div>
        </div>

    <!-- ******************************************************************************* -->
        <div id="list-cerpani" class="content scaffold-list" role="main">
            <h1>Čerpání</h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${details['listCerpani']}" />

            <div class="pagination">
                <g:paginate total="${cerpaniCount ?: 0}" />
            </div>
        </div>

        <div id="list-uver" class="content scaffold-list" role="main">
            <h1>Splátky</h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${details['listSplatky']}" />

            <div class="pagination">
                <g:paginate total="${splatkyCount ?: 0}" />
            </div>
        </div>

    </body>
</html>