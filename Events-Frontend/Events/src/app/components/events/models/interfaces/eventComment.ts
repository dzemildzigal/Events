import { event } from './event';

export interface eventComment{
    eventCommentId:number;
    userId:number;
    event:event;
    text:string;
}