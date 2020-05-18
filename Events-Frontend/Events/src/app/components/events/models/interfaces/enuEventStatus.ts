import {event} from "./event";

export interface enuEventStatus {
    eventStatusId:number;
    description:string;
    events:Array<event>;
}