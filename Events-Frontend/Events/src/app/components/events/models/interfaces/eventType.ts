import { event } from './event';

export interface eventType {
    eventTypeId:number;
    eventTypeDescription:string;
    eventList:Array<event>;
}