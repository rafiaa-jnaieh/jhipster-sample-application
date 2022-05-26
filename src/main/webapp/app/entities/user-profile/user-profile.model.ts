import { IDashboardLayout } from 'app/entities/dashboard-layout/dashboard-layout.model';
import { IDashboardItem } from 'app/entities/dashboard-item/dashboard-item.model';

export interface IUserProfile {
  id?: string;
  name?: string | null;
  priority?: number | null;
  dashboardLayout?: IDashboardLayout | null;
  dashboardItem?: IDashboardItem | null;
}

export class UserProfile implements IUserProfile {
  constructor(
    public id?: string,
    public name?: string | null,
    public priority?: number | null,
    public dashboardLayout?: IDashboardLayout | null,
    public dashboardItem?: IDashboardItem | null
  ) {}
}

export function getUserProfileIdentifier(userProfile: IUserProfile): string | undefined {
  return userProfile.id;
}
