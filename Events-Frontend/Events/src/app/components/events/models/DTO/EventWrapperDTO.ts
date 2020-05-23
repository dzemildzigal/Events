import { event } from '../interfaces/event';
import { enuEventStatus } from '../interfaces/enuEventStatus';
import { eventType } from '../interfaces/eventType';
import { location } from '../interfaces/location';



export interface EventWrapperDTO {
    event:event;
    location:location;
    enuEventStatus:enuEventStatus;
    eventType:eventType;
    userId:number;
}