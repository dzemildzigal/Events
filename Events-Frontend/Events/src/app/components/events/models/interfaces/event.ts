import { location } from "./location";
import { eventType } from './eventType';
import { enuEventStatus } from './enuEventStatus';
import { eventComment } from './eventComment';
import { userEventRegistration } from './userEventRegistration';

export interface event {
    eventId:number;
    eventName:string;
    description:string;
    location:location;
    eventType:eventType;
    canBuyTicket:boolean;
    ticketPrice:number;
    numberOfTicketsAvailable:number;
    eventPictureURL:string;
    createdByUserId:number;

    enuEventStatus:enuEventStatus;
    eventTime:Date;
    eventCommentsList:Array<eventComment>;
    userEventRegistrationList:Array<userEventRegistration>;

}