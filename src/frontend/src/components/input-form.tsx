"use client";

import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { z } from "zod";

import { Button } from "@/components/ui/button";
import {
  Form,
  FormControl,
  FormDescription,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { RadioGroup, RadioGroupItem } from "./ui/radio-group";

const FormSchema = z.object({
  start: z.string().min(2, {
    message: "Username must be at least 2 characters.",
  }),
  goal: z.string().min(2, {
    message: "Username must be at least 2 characters.",
  }),
  algorithm: z.enum(["UCS", "AStar", "GBeFS"], {
    required_error: "Please select an algorithm.",
  }),
});

export function InputForm({
  handleSubmit,
}: {
  handleSubmit: (data: any) => void;
}) {
  const form = useForm<z.infer<typeof FormSchema>>({
    resolver: zodResolver(FormSchema),
    defaultValues: {
      start: "",
      goal: "",
      algorithm: "UCS",
    },
  });

  function onSubmit(data: z.infer<typeof FormSchema>) {
    console.log(data);
    handleSubmit(data);
  }

  return (
    <Form {...form}>
      <form
        onSubmit={form.handleSubmit(onSubmit)}
        className='w-2/3 space-y-6 flex flex-col'
      >
        <FormField
          control={form.control}
          name='start'
          render={({ field }) => (
            <>
              <FormItem>
                <FormLabel>Start</FormLabel>
                <FormControl>
                  <Input placeholder='Fill the start word in here' {...field} />
                </FormControl>
                <FormDescription>
                  This is the starting point of the algorithm.
                </FormDescription>
                <FormMessage />
              </FormItem>
            </>
          )}
        />

        <FormField
          control={form.control}
          name='goal'
          render={({ field }) => (
            <>
              <FormItem>
                <FormLabel>Goal</FormLabel>
                <FormControl>
                  <Input placeholder='Fill the goal word in here' {...field} />
                </FormControl>
                <FormDescription>
                  This is the goal point of the algorithm.
                </FormDescription>
                <FormMessage />
              </FormItem>
            </>
          )}
        />

        <FormField
          control={form.control}
          name='algorithm'
          render={({ field }) => (
            <FormItem className='space-y-3'>
              <FormLabel>Please select the algorithm...</FormLabel>
              <FormControl>
                <RadioGroup
                  onValueChange={field.onChange}
                  defaultValue={field.value}
                  className='flex flex-col space-y-1'
                >
                  <FormItem className='flex items-center space-x-3 space-y-0'>
                    <FormControl>
                      <RadioGroupItem value='UCS' />
                    </FormControl>
                    <FormLabel className='font-normal'>
                      Uniform cost search
                    </FormLabel>
                  </FormItem>
                  <FormItem className='flex items-center space-x-3 space-y-0'>
                    <FormControl>
                      <RadioGroupItem value='AStar' />
                    </FormControl>
                    <FormLabel className='font-normal'>Astar</FormLabel>
                  </FormItem>
                  <FormItem className='flex items-center space-x-3 space-y-0'>
                    <FormControl>
                      <RadioGroupItem value='GBeFS' />
                    </FormControl>
                    <FormLabel className='font-normal'>
                      Greedy best-first search
                    </FormLabel>
                  </FormItem>
                </RadioGroup>
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />

        <Button type='submit'>Submit</Button>
      </form>
    </Form>
  );
}
