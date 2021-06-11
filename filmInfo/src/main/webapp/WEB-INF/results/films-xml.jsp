<?xml version="1.0" encoding="UTF-8"?>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<films>
<c:forEach items="${films}" var="film">
    <film>
    	<id>${film.id}</id>
    	<title>${fn:escapeXml(film.title)}</title>
    	<year>${film.year}</year>
    	<director>${fn:escapeXml(film.director)}</director>
    	<stars>${fn:escapeXml(film.stars)}</stars>
    	<review>${fn:escapeXml(film.review)}</review>
    </film>
</c:forEach>
</films>