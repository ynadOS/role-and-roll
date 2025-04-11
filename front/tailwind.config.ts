import type { Config } from 'tailwindcss';

const config: Config = {
  darkMode: 'class', // Active le dark mode via .dark sur <html>
  content: [
    './src/**/*.{html,ts}',
  ],
  theme: {
    extend: {
      colors: {
        brand: {
          DEFAULT: '#3B82F6', // bleu clair (ex: pour Role & Roll)
          dark: '#1E3A8A',
        },
        background: {
          light: '#ffffff',
          dark: '#1f2937', // gris foncé Tailwind
        },
      },
      transitionProperty: {
        'theme': 'background-color, border-color, color, fill, stroke',
      },
      borderRadius: {
        'xl': '1rem',
        '2xl': '1.5rem',
      },
    },
  },
  plugins: [],
};

export default config;
