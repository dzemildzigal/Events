import { enuRegistrationType } from './enuRegistrationType';
import { event } from './event';
export interface userEventRegistration {
    userEventRegistrationId:number;
    userId:number;
    enuRegistrationType:enuRegistrationType;
    event:event;
}