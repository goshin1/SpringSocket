const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = (app) => {
    app.use(
        "/socket",
        createProxyMiddleware({
        target: "http://localhost:8080",
        changeOrigin:true,
        ws : true
        })
    );
};