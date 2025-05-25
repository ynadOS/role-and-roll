import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { environment } from '../../environments/environments';

@Injectable({
  providedIn: 'root'
})
export class NotificationClientService {
  private stompClient: any;
  private subscription?: any;
  private notificationSubject = new BehaviorSubject<any>(null);

  public notifications$ = this.notificationSubject.asObservable();

  constructor() {
    Promise.all([
      import('@stomp/stompjs'),
      import('sockjs-client')
    ]).then(([stomp, SockJS]) => {
      const Client = stomp.Client;

      this.stompClient = new Client({
        webSocketFactory: () => new SockJS.default(`${environment.apiUrl.replace('/api', '')}/ws/notifications`),
        reconnectDelay: 5000
      });

      this.stompClient.onConnect = () => {
        this.subscription = this.stompClient.subscribe('/user/queue/notifications', (message: any) => {
          const body = JSON.parse(message.body);
          this.notificationSubject.next(body);
        });
      };

      this.stompClient.activate();
    });
  }

  disconnect() {
    this.subscription?.unsubscribe();
    this.stompClient.deactivate();
  }
}