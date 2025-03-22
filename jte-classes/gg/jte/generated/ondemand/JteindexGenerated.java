package gg.jte.generated.ondemand;
import hexlet.code.dto.BasePage;
@SuppressWarnings("unchecked")
public final class JteindexGenerated {
	public static final String JTE_NAME = "index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,1,1,1,3,3,6,6,24,24,24,24,24,24,1,1,1,1};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, hexlet.code.dto.BasePage page) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n<div class=\"jumbotron d-flex align-items-center min-vh-100 \">\n    <div class=\"container text-center border rounded border p-3 bg-light \" >\n        <form action=\"/urls\" method=\"post\" class=\"g-3\">\n            <div class=\"mb-3\">\n                <div class=\"form-text text-center\">\n                    <h1>Бесплатная проверка сайтов на SEO пригодность.</h1>\n                        Введите ссылку для проверки.\n                </div>\n                <label for=\"floatingInput\"></label>\n                <input type=\"url\" name=\"url\" autofocus class=\"form-control rounded-pill\" id=\"floatingInput\"\n                       placeholder=\"https://www.example.com\" autocomplete=\"off\">\n            </div>\n            <button type=\"submit\" class=\"btn btn-secondary\">Отправить на проверку</button>\n\n        </form>\n    </div>\n</div>\n");
			}
		}, page);
		jteOutput.writeContent(")");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		hexlet.code.dto.BasePage page = (hexlet.code.dto.BasePage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
