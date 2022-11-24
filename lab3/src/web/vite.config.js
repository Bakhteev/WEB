import {resolve} from 'path'
import {defineConfig} from 'vite'

export default defineConfig({
    root: 'src',
    build: {
        rollupOptions: {
            input: {
                index: resolve(__dirname, 'src', 'index.js'),
                start: resolve(__dirname, 'src', 'start.js')
            },
            output: {
                dir: resolve(__dirname, '..', 'main', 'webapp', 'resources'),
                entryFileNames: `js/[name].js`,
                chunkFileNames: `[name].js`,
                assetFileNames: `css/[name].[ext]`,

            },
        },
    },
})
