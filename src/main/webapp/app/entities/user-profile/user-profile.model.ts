import { IDashboardItem } from 'app/entities/dashboard-item/dashboard-item.model';
import { IDashboardLayout } from 'app/entities/dashboard-layout/dashboard-layout.model';
import { IDashboardConfig } from 'app/entities/dashboard-config/dashboard-config.model';

export interface IUserProfile {
  id?: string;
  name?: string | null;
  priority?: number | null;
  dashboardItems?: IDashboardItem[] | null;
  dashboardLayout?: IDashboardLayout | null;
  dashboardConfig?: IDashboardConfig | null;
}

export class UserProfile implements IUserProfile {
  constructor(
    public id?: string,
    public name?: string | null,
    public priority?: number | null,
    public dashboardItems?: IDashboardItem[] | null,
    public dashboardLayout?: IDashboardLayout | null,
    public dashboardConfig?: IDashboardConfig | null
  ) {}
}

export function getUserProfileIdentifier(userProfile: IUserProfile): string | undefined {
  return userProfile.id;
}
