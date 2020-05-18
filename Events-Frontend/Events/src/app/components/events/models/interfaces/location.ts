import { event } from './event';

export interface location {
    locationId:number;
    description:string;
    eventList:Array<event>;
}