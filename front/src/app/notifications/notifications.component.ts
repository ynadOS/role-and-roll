// import { Component, OnInit } from '@angular/core';
// import { NotificationClientService } from '../services/notification-client.service';
// import { CommonModule } from '@angular/common';

// @Component({
//   selector: 'app-notifications',
//   standalone: true,
//   imports: [CommonModule], 
//   templateUrl: './notifications.component.html',
//   styleUrl: './notifications.component.css',
// })
// export class NotificationsComponent implements OnInit {
//   notifications: any[] = [];

//   constructor(private notificationClient: NotificationClientService) {}

//   ngOnInit(): void {
//     this.notificationClient.notifications$.subscribe(notification => {
//       if (notification) {
//         this.notifications.unshift(notification);
//       }
//     });
//   }
// }
