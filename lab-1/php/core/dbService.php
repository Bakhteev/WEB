<?php

require __DIR__ . '/../../vendor/autoload.php';

use Kreait\Firebase\Factory;


class DatabaseService
{
  private $database;
  public function __construct()
  {
    $factory = (new Factory)
      ->withServiceAccount(__DIR__ . '/../web-lab-1-455e5-firebase-adminsdk-o7g4x-d608b45418.json')
      ->withDatabaseUri('https://web-lab-1-455e5-default-rtdb.europe-west1.firebasedatabase.app');

    $this->database = $factory->withDatabaseAuthVariableOverride(null)->createDatabase();
  }

  public function getData()
  {
    return array_values($this->database->getReference('/data')->getValue());
  }

  public function getLast()
  {
    return array_values($this->database->getReference('/data')->orderByKey()->limitToLast(1)->getValue());
  }

  public function setData(
    $x,
    $y,
    $r,
    $hitted,
    $currentTime,
    $execution_time
  ) {
    $uid =  time() + 3600 * (3 + date("I"));
    $this->database->getReference('/data/' . $uid)->set([
      "x" => $x,
      "y" => $y,
      "r" => $r,
      "hitted" => $hitted,
      "current_time" => $currentTime,
      "execution_time" => $execution_time
    ]);
  }
}
