package gg.jte.generated.ondemand;
import hexlet.code.dto.UrlPage;
import hexlet.code.util.NamedRoutes;
@SuppressWarnings("unchecked")
public final class JtecheckGenerated {
	public static final String JTE_NAME = "check.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,2,4,4,7,7,13,13,13,14,14,14,14,14,14,14,14,14,26,26,26,34,34,34,43,43,43,55,55,55,58,58,58,61,61,61,68,68,68,68,68,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlPage page) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n\n    <div class=\"jumbotron d-flex align-items-center min-vh-100 \">\n        <div class=\"container text-center border rounded border p-3 bg-light \" >\n            <div class=\"row\">\n                <h1 class=\"col align-items-right\">\n                    Сайт: ");
				jteOutput.setContext("h1", null);
				jteOutput.writeUserContent(page.getUrl().getName());
				jteOutput.writeContent("\n                    <form method=\"post\"");
				var __jte_html_attribute_0 = NamedRoutes.checkPath(page.getUrl().getId());
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
					jteOutput.writeContent(" action=\"");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(__jte_html_attribute_0);
					jteOutput.setContext("form", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(">\n                        <button type=\"submit\" class=\"btn btn-secondary\">Запустить проверку</button>\n                    </form>\n                </h1>\n            </div>\n\n            <table class=\"table\">\n                <tr>\n                    <td>\n                        ID\n                    </td>\n                    <td>\n                        ");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getId());
				jteOutput.writeContent("\n                    </td>\n                </tr>\n                <tr>\n                    <td>\n                        Имя\n                    </td>\n                    <td>\n                        ");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getName());
				jteOutput.writeContent("\n                    </td>\n\n                </tr>\n                <tr>\n                    <td>\n                        Дата создания\n                    </td>\n                    <td>\n                        ");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getCreatedAt().format(page.getFormatter()));
				jteOutput.writeContent("\n                    </td>\n                </tr>\n\n            </table>\n\n            <h1>Проверки</h1>\n\n            <table class=\"table table-striped\">\n\n                    <tr>\n                        <td>\n                            ");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getId());
				jteOutput.writeContent("\n                        </td>\n                        <td>\n                            ");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getName());
				jteOutput.writeContent("\n                        </td>\n                        <td>\n                            ");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getCreatedAt().format(page.getFormatter()));
				jteOutput.writeContent("\n                        </td>\n                    </tr>\n\n            </table>\n        </div>\n    </div>\n");
			}
		}, page);
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlPage page = (UrlPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
