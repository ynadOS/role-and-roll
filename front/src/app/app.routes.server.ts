import { RenderMode, ServerRoute } from '@angular/ssr';
import { firstValueFrom } from 'rxjs';
import { inject } from '@angular/core';
import { CampaignService } from './../app/services/campaigns.service';  // ajuste ce chemin

export const serverRoutes: ServerRoute[] = [
  {
    path: 'campaigns/:id',
    renderMode: RenderMode.Prerender,
async getPrerenderParams(): Promise<Record<string, string>[]> {
  const service = inject(CampaignService);
  let ids: (string | number)[];
  try {
    ids = await firstValueFrom(service.getAllCampaignIds());
  } catch(err) {
    console.warn('Erreur pendant getAllCampaignIds dans prerender :', err);
    return [];  // ou un fallback raisonnable
  }
  return ids.map(id => ({ id: id.toString() }));
}
  },
  {
    path: '**',
    renderMode: RenderMode.Prerender
  }
];