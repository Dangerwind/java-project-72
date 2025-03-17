
@import hexlet.code.dto.UrlPage
@param UrlPage page

@template.layout.page(
content = @`
<div class="jumbotron d-flex align-items-center min-vh-100 ">
    <div class="container text-center border rounded border p-3 bg-light " >
        <form action="/urls" method="post" class="g-3">
            <div class="mb-3">
                <div class="form-text text-center">
                    Бесплатная проверка сайтов на SEO пригодность.
                    Введите ссылку для проверки.
                </div>
                <label for="floatingInput"></label>
                <input type="url" name="url" autofocus class="form-control rounded-pill" id="floatingInput"
                                                          placeholder="https://www.example.com" autocomplete="off">
            </div>
            <button type="submit" class="btn btn-secondary">Отправить на проверку</button>

        </form>
    </div>
</div>
`,
footer = @`
    <p>Футтер всех пользователй</p>
`
)