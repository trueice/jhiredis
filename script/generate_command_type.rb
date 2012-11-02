#!/usr/bin/env ruby

require 'json'
require 'open-uri'

data = open("https://raw.github.com/antirez/redis-doc/master/commands.json").read()
commands = JSON.parse(data).select do |cmd, attrs|
  cmd !~ / /
end

size = commands.size
i = 0
commands.each do |cmd, attrs|
  puts "/**"
  puts " * #{attrs["summary"]}.<br>"
  arguments = attrs["arguments"] || []
  if arguments.size > 0
    puts " * arguments:"
    puts " * <ol>"
    arguments.each_with_index do |arg, index|
      optional = arg["optional"]
      puts " * <li>#{arg["name"]}(#{arg["type"]})#{optional ? " optional" : ""}</li>"
    end
    puts " * </ol>"
  end
  puts " * since: #{attrs["since"]}<br>" if attrs["since"]
  puts " * group: #{attrs["group"]}<br>" if attrs["group"]
  puts " */"
  puts "#{cmd.gsub(/ /, "_")}#{i == size -1 ? ";" : ","}"
  puts
  i += 1
end
