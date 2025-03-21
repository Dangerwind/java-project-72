@import hexlet.code.util.NamedRoutes

@import hexlet.code.dto.UrlsPage
@param UrlsPage page


@template.layout.page(
page = page,
content = @`

<div class="jumbotron d-flex align-items-center min-vh-100 ">
    <div class="container text-center border rounded border p-3 bg-light " >
        <h1>Сайты </h1>

        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Имя</th>
                <th scope="col">Последняя проверка</th>
                <th scope="col">Код ответа</th>
            </tr>
            </thead>
            @for(var product : page.getUrls())
                <tr>
                    <td>
                            ${product.getId()}
                    </td>
                    <td>
                        <a href="${NamedRoutes.urlPath(product.getId())}">
                            ${product.getName()}
                        </a>
                    </td>
                    <td>
                      ${product.getCreatedAt().format(page.getFormatter())}
                    </td>
                    <td>
                        пусто
                    </td>
                </tr>
            @endfor

        </table>

    </div>
</div>
`,
))