@import hexlet.code.dto.UrlPage
@import hexlet.code.util.NamedRoutes
@param UrlPage page

@template.layout.page(
page = page,
content = @`

    <div class="jumbotron d-flex align-items-center min-vh-100 ">
        <div class="container text-center border rounded border p-3 bg-light " >
            <div class="row">
                <h1 class="col align-items-right">
                    Сайт: ${page.getUrl().getName()}
                    <form method="post" action="${NamedRoutes.checkPath(page.getUrl().getId())}">
                        <button type="submit" class="btn btn-secondary">Запустить проверку</button>
                    </form>
                </h1>
            </div>

            <table class="table">
                <tr>
                    <td>
                        ID
                    </td>
                    <td>
                        ${page.getUrl().getId()}
                    </td>
                </tr>
                <tr>
                    <td>
                        Имя
                    </td>
                    <td>
                        ${page.getUrl().getName()}
                    </td>

                </tr>
                <tr>
                    <td>
                        Дата создания
                    </td>
                    <td>
                        ${page.getUrl().getCreatedAt().format(page.getFormatter())}
                    </td>
                </tr>

            </table>

            <h1>Проверки</h1>

            <table class="table table-striped">

                    <tr>
                        <td>
                            ${page.getUrl().getId()}
                        </td>
                        <td>
                            ${page.getUrl().getName()}
                        </td>
                        <td>
                            ${page.getUrl().getCreatedAt().format(page.getFormatter())}
                        </td>
                    </tr>

            </table>
        </div>
    </div>
`,
)