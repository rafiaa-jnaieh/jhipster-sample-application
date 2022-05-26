import { IDashboardItem } from 'app/entities/dashboard-item/dashboard-item.model';
import { IDashboardLayout } from 'app/entities/dashboard-layout/dashboard-layout.model';

export interface IUserProfile {
  id?: string;
  name?: string | null;
  priority?: number | null;
  dashboardItems?: IDashboardItem[] | null;
  dashboardLayout?: IDashboardLayout | null;
}

export class UserProfile implements IUserProfile {
  constructor(
    public id?: string,
    public name?: string | null,
    public priority?: number | null,
    public dashboardItems?: IDashboardItem[] | null,
    public dashboardLayout?: IDashboardLayout | null
  ) {}
}

export function getUserProfileIdentifier(userProfile: IUserProfile): string | undefined {
  return userProfile.id;
}
