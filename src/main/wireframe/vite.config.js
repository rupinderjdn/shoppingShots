import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";
// import { ViteMinifyPlugin } from "vite-plugin-minify";
// import { compression } from "vite-plugin-compression2";

export default ({ mode }) => {
  return defineConfig({
    plugins: [
      react(),
      // ViteMinifyPlugin({}),
      // compression({
      //   algorithm: "brotliCompress",
      // }),
    ],
    build: {
      outDir: "buildFolder",
      assetsDir: "assetsFolder",
      assetsInlineLimit: 4096,
      sourcemap: mode === "dev",
      rollupOptions: {
        external: (id) => {
          // if(id.includes("gif"))console.log(id);
          // the gifs and svg doesn't come here maybe because its a direct part of index.html
          if (id.endsWith("png") || id.endsWith("jpg") || id.endsWith("webp") || id.endsWith("gif") || id.endsWith("svg")) return true;
        }, // Exclude image file types

        output: {
          dir: "../resources/static/",
          entryFileNames: "js/bundle.js",
          assetFileNames: "css/bundle[extname]",
        },
      },
    },
  });
};

