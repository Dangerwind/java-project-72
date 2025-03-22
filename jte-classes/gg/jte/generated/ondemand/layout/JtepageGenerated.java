package gg.jte.generated.ondemand.layout;
import hexlet.code.dto.BasePage;
import gg.jte.Content;
@SuppressWarnings("unchecked")
public final class JtepageGenerated {
	public static final String JTE_NAME = "layout/page.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,2,20,20,32,32,33,33,34,36,36,36,39,39,40,42,42,42,45,45,46,48,48,48,51,51,52,52,56,56,56,72,72,72,2,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, Content content, BasePage page) {
		jteOutput.writeContent("\n<!DOCTYPE html>\n<html lang=\"ru\">\n<head>\n    <meta charset=\"utf-8\">\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n    <title>Анализатор страниц</title>\n    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\"\n          rel=\"stylesheet\" integrity=\"sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH\"\n          crossorigin=\"anonymous\">\n\n</head>\n<body>\n\n\n\n");
		jteOutput.writeContent("\n\n<nav class=\"text-start border p-3 bg-light\">\n    <div class=\"btn-group\" role=\"group\" aria-label=\"Простой пример\">\n        <button type=\"button\" class=\"btn btn-secondary disabled\" tabindex=\"-1\" role=\"button\" aria-disabled=\"true\">\n            Анализатор страниц\n        </button>\n        <a href=\"/\" type=\"button\" class=\"btn btn-secondary\">Главная</a>\n        <a href=\"urls/\" type=\"button\" class=\"btn btn-secondary\">Сайты</a>\n    </div>\n</nav>\n\n ");
		if (page != null && page.getFlashType() != null && page.getFlashMessage() != null) {
			jteOutput.writeContent("\n    ");
			if (page.getFlashType().equals("info")) {
				jteOutput.writeContent("\n        ");
				jteOutput.writeContent("\n        <div class=\"rounded-0 m-0 alert alert-dismissible fade show alert-info\" role=\"alert\">\n            <p class=\"m-0\">");
				jteOutput.setContext("p", null);
				jteOutput.writeUserContent(page.getFlashMessage());
				jteOutput.writeContent("</p>\n            <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\n        </div>\n    ");
			} else if (page.getFlashType().equals("danger")) {
				jteOutput.writeContent("\n        ");
				jteOutput.writeContent("\n        <div class=\"rounded-0 m-0 alert alert-dismissible fade show alert-danger\" role=\"alert\">\n            <p class=\"m-0\">");
				jteOutput.setContext("p", null);
				jteOutput.writeUserContent(page.getFlashMessage());
				jteOutput.writeContent("</p>\n            <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\n        </div>\n    ");
			} else if (page.getFlashType().equals("success")) {
				jteOutput.writeContent("\n        ");
				jteOutput.writeContent("\n        <div class=\"rounded-0 m-0 alert alert-dismissible fade show alert-success\" role=\"alert\">\n            <p class=\"m-0\">");
				jteOutput.setContext("p", null);
				jteOutput.writeUserContent(page.getFlashMessage());
				jteOutput.writeContent("</p>\n            <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\n        </div>\n    ");
			}
			jteOutput.writeContent("\n ");
		}
		jteOutput.writeContent("\n\n\n<div>\n    ");
		jteOutput.setContext("div", null);
		jteOutput.writeUserContent(content);
		jteOutput.writeContent("\n</div>\n\n<footer class=\"fixed-bottom text-center border p-3 bg-light\">\n    <a href=\"https://github.com/Dangerwind\" target=\"_blank\" class=\"btn btn-secondary\"\n       tabindex=\"-1\" role=\"button\" aria-disabled=\"true\">\n        Сайт разработан мной в 2025 году\n    </a>\n</footer>\n\n\n<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js\"\n        integrity=\"sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz\"\n        crossorigin=\"anonymous\"></script>\n\n</body>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		Content content = (Content)params.get("content");
		BasePage page = (BasePage)params.getOrDefault("page", null);
		render(jteOutput, jteHtmlInterceptor, content, page);
	}
}
