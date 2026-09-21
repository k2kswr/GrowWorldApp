import {render,screen} from "@testing-library/react";
import {describe,it,expect,vi} from "vitest";
import {ActivityForm} from "./ActivityForm";
describe("ActivityForm",()=>{it("shows the EXP preview",()=>{render(<ActivityForm onSubmit={vi.fn()}/>);expect(screen.getByText("+30 EXP を記録")).toBeTruthy();});});
