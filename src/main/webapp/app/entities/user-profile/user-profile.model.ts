import { IDashboardLayout } from 'app/entities/dashboard-layout/dashboard-layout.model';
import { IDashboardItem } from 'app/entities/dashboard-item/dashboard-item.model';

export interface IUserProfile {
  id?: string;
  name?: string | null;
  priority?: number | null;
  dashboardLayouts?: IDashboardLayout[] | null;
  dashboardItems?: IDashboardItem[] | null;
}

export class UserProfile implements IUserProfile {
  constructor(
    public id?: string,
    public name?: string | null,
    public priority?: number | null,
    public dashboardLayouts?: IDashboardLayout[] | null,
    public dashboardItems?: IDashboardItem[] | null
  ) {}
}

export function getUserProfileIdentifier(userProfile: IUserProfile): string | undefined {
  return userProfile.id;
}
