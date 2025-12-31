// @ts-check
import { defineConfig } from 'astro/config';
import svelte from "@astrojs/svelte"
import deno from "@deno/astro-adapter"

// https://astro.build/config
export default defineConfig({
    adapter: deno(),
    integrations: [svelte()],
});
