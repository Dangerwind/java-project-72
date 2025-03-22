package gg.jte.generated.ondemand;
import hexlet.code.util.NamedRoutes;
import hexlet.code.dto.UrlsPage;
@SuppressWarnings("unchecked")
public final class JteurlslistGenerated {
	public static final String JTE_NAME = "urlslist.jte";
	public static final int[] JTE_LINE_INFO = {0,0,2,3,3,3,3,6,6,9,9,24,24,27,27,27,30,30,30,30,30,30,30,30,30,31,31,31,35,35,35,41,41,47,47,47,47,47,47,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlsPage page) {
		jteOutput.writeContent("\n\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n\n<div class=\"jumbotron d-flex align-items-center min-vh-100 \">\n    <div class=\"container text-center border rounded border p-3 bg-light \" >\n        <h1>Сайты </h1>\n\n        <table class=\"table table-striped\">\n            <thead>\n            <tr>\n                <th scope=\"col\">ID</th>\n                <th scope=\"col\">Имя</th>\n                <th scope=\"col\">Последняя проверка</th>\n                <th scope=\"col\">Код ответа</th>\n            </tr>\n            </thead>\n            ");
				for (var product : page.getUrls()) {
					jteOutput.writeContent("\n                <tr>\n                    <td>\n                            ");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(product.getId());
					jteOutput.writeContent("\n                    </td>\n                    <td>\n                        <a");
					var __jte_html_attribute_0 = NamedRoutes.urlPath(product.getId());
					if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
						jteOutput.writeContent(" href=\"");
						jteOutput.setContext("a", "href");
						jteOutput.writeUserContent(__jte_html_attribute_0);
						jteOutput.setContext("a", null);
						jteOutput.writeContent("\"");
					}
					jteOutput.writeContent(">\n                            ");
					jteOutput.setContext("a", null);
					jteOutput.writeUserContent(product.getName());
					jteOutput.writeContent("\n                        </a>\n                    </td>\n                    <td>\n                      ");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(product.getCreatedAt().format(page.getFormatter()));
					jteOutput.writeContent("\n                    </td>\n                    <td>\n                        пусто\n                    </td>\n                </tr>\n            ");
				}
				jteOutput.writeContent("\n\n        </table>\n\n    </div>\n</div>\n");
			}
		}, page);
		jteOutput.writeContent(")");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlsPage page = (UrlsPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
