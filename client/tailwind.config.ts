import type { Config } from "tailwindcss"

const config: Config = {
  content: [
    "./pages/**/*.{js,ts,jsx,tsx,mdx}",
    "./components/**/*.{js,ts,jsx,tsx,mdx}",
    "./app/**/*.{js,ts,jsx,tsx,mdx}",
  ],
  theme: {
    extend: {
      colors: {
        "primary-color": "var(--primary-color)",
        "secondary-color": "var(--secondary-color)",
        "menu-color": "var(--menu-color)",
        "submenu-color": "var(--submenu-color)",
        "background-color": "var(--background-color)",
        "text-menu": "var(--text-menu)",
      },
    },
  },
  plugins: [],
}
export default config
