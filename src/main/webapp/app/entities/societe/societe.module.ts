import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RhPlusSharedModule } from 'app/shared/shared.module';
import { SocieteComponent } from './societe.component';
import { SocieteDetailComponent } from './societe-detail.component';
import { SocieteUpdateComponent } from './societe-update.component';
import { SocieteDeleteDialogComponent } from './societe-delete-dialog.component';
import { societeRoute } from './societe.route';

@NgModule({
  imports: [RhPlusSharedModule, RouterModule.forChild(societeRoute)],
  declarations: [SocieteComponent, SocieteDetailComponent, SocieteUpdateComponent, SocieteDeleteDialogComponent],
  entryComponents: [SocieteDeleteDialogComponent],
})
export class RhPlusSocieteModule {}
