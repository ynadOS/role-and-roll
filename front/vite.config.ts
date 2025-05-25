import { defineConfig } from 'vite';

export default defineConfig({
  optimizeDeps: {
    include: ['@stomp/stompjs', 'sockjs-client'],
    exclude: ['@angular/core', '@angular/platform-browser']
  },
  ssr: {
    noExternal: ['@stomp/stompjs', 'sockjs-client']
  }
});