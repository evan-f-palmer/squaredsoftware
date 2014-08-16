--require this example in interactive mode to change values
--In interactive mode you can change rgb to whatever you want

Gdx = luajava.bindClass("com.badlogic.gdx.Gdx")
GL20 = luajava.bindClass("com.badlogic.gdx.graphics.GL20")

require("WrapApp")
require("GdxDesktop")

app = WrapApp.createBlankApp()
r = 0; g = 0; b = 0;
renderTest = function() Gdx.gl:glClearColor(r, g, b, 1); Gdx.gl:glClear(GL20.GL_COLOR_BUFFER_BIT); end
app.render = renderTest

wrapedApp = WrapApp.wrapApp(app)
GdxDesktop.setApp(wrapedApp)
GdxDesktop.start()
