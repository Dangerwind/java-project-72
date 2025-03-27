@import hexlet.code.dto.BasePage
@param hexlet.code.dto.BasePage page

@template.layout.page(
page = page,
content = @`
    <div class="jumbotron d-flex align-items-center min-vh-100 ">
        <div class="container text-center border rounded border p-3 bg-light " >

                <div class="mb-3">
                    <div class="form-text text-center">
                        <h1>Ошибка 404</h1>
                        <h2>Такой страницы нет</h2>
                    </div>
                </div>

        </div>
    </div>
`
))