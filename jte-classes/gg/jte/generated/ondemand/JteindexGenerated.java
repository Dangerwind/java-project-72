package gg.jte.generated.ondemand;
import hexlet.code.dto.UrlPage;
@SuppressWarnings("unchecked")
public final class JteindexGenerated {
	public static final String JTE_NAME = "index.jte";
	public static final int[] JTE_LINE_INFO = {1,1,2,2,2,2,4,4,7,7,25,25,25,25,27,27,27,27,27,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlPage page) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n<div class=\"jumbotron d-flex align-items-center min-vh-100 \">\n    <div class=\"container text-center border rounded border p-3 bg-light \" >\n        <form action=\"/urls\" method=\"post\" class=\"g-3\">\n            <div class=\"mb-3\">\n                <div class=\"form-text text-center\">\n                    Бесплатная проверка сайтов на SEO пригодность.\n                    Введите ссылку для проверки.\n                </div>\n                <label for=\"floatingInput\"></label>\n                <input type=\"url\" name=\"url\" autofocus class=\"form-control rounded-pill\" id=\"floatingInput\"\n                                                          placeholder=\"https://www.example.com\" autocomplete=\"off\">\n            </div>\n            <button type=\"submit\" class=\"btn btn-secondary\">Отправить на проверку</button>\n\n        </form>\n    </div>\n</div>\n");
			}
		}, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <p>Футтер всех пользователй</p>\n");
			}
		});
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlPage page = (UrlPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
