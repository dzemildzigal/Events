import { userEventRegistration } from './userEventRegistration';

export interface enuRegistrationType {
    enuRegistrationTypeId:number;
    description:string;
    userEventRegistrationList:Array<userEventRegistration>;
}