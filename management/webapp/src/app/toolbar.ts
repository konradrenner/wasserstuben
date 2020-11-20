export declare interface ToolbarPossibilites{
    toolbarType: string;
    searchActive(): boolean;
    performSearch(search: string): void;
}