<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach items="${films}" var="film">
    ${film.id}#${film.title}#${film.year}#${film.director}#${film.stars}#${film.review}
</c:forEach>